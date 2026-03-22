#!/bin/bash
# =============================================================
# 図書管理システム - CloudFormation デプロイスクリプト
# スタックは依存関係の順番通りに作成すること
# =============================================================

set -e  # エラー発生時に即停止

PROJECT_NAME="book-management"
REGION="ap-northeast-1"
TEMPLATES_DIR="./templates"

# 色付きログ
log_info()  { echo -e "\033[32m[INFO]\033[0m $1"; }
log_warn()  { echo -e "\033[33m[WARN]\033[0m $1"; }
log_error() { echo -e "\033[31m[ERROR]\033[0m $1"; }

# スタック作成 or 更新
deploy_stack() {
  local stack_name=$1
  local template_file=$2
  local parameters=$3

  log_info "Deploying: ${stack_name}"

  aws cloudformation deploy \
    --stack-name "${stack_name}" \
    --template-file "${template_file}" \
    --parameter-overrides ProjectName="${PROJECT_NAME}" ${parameters} \
    --capabilities CAPABILITY_NAMED_IAM \
    --region "${REGION}"

  log_info "Done: ${stack_name}"
}

# =============================================================
# デプロイ順序（依存関係を考慮）
# =============================================================

# 1. VPC・ネットワーク基盤（他の全スタックが依存）
deploy_stack \
  "${PROJECT_NAME}-vpc" \
  "${TEMPLATES_DIR}/01_vpc.yaml"

# 2. セキュリティグループ・NACL（VPCが必要）
deploy_stack \
  "${PROJECT_NAME}-security-groups" \
  "${TEMPLATES_DIR}/02_security_groups.yaml"

# 3. S3バケット（LambdaとEC2が依存）
deploy_stack \
  "${PROJECT_NAME}-s3" \
  "${TEMPLATES_DIR}/07_s3.yaml"

# 4. RDS（セキュリティグループが必要）
log_warn "RDS作成には約10分かかります..."
read -p "DBパスワードを入力してください: " -s DB_PASSWORD
echo ""
deploy_stack \
  "${PROJECT_NAME}-rds" \
  "${TEMPLATES_DIR}/06_rds.yaml" \
  "DBPassword=${DB_PASSWORD}"

# 5. Cognito（ALBより先に作成が必要）
ALB_DNS=$(aws cloudformation describe-stacks \
  --stack-name "${PROJECT_NAME}-alb" \
  --query 'Stacks[0].Outputs[?OutputKey==`AlbDnsName`].OutputValue' \
  --output text --region "${REGION}" 2>/dev/null || echo "placeholder")

deploy_stack \
  "${PROJECT_NAME}-cognito" \
  "${TEMPLATES_DIR}/04_cognito.yaml" \
  "AlbDnsName=${ALB_DNS}"

# 6. ALB（Cognitoが必要）
read -p "ACM証明書のARNを入力してください: " CERT_ARN
deploy_stack \
  "${PROJECT_NAME}-alb" \
  "${TEMPLATES_DIR}/03_alb.yaml" \
  "CertificateArn=${CERT_ARN}"

# 7. EC2・AutoScaling（ALBのターゲットグループが必要）
deploy_stack \
  "${PROJECT_NAME}-ec2" \
  "${TEMPLATES_DIR}/05_ec2.yaml"

# 8. Lambda・EventBridge（RDS・S3・セキュリティグループが必要）
deploy_stack \
  "${PROJECT_NAME}-lambda" \
  "${TEMPLATES_DIR}/08_lambda.yaml"

# 9. SSM Session Manager設定（EC2・S3が必要）
deploy_stack \
  "${PROJECT_NAME}-ssm" \
  "${TEMPLATES_DIR}/09_ssm.yaml"

log_info "=============================="
log_info "デプロイ完了！"
log_info "ALB DNS: $(aws cloudformation describe-stacks \
  --stack-name ${PROJECT_NAME}-alb \
  --query 'Stacks[0].Outputs[?OutputKey==`AlbDnsName`].OutputValue' \
  --output text --region ${REGION})"
log_info "=============================="