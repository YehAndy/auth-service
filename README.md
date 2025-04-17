# Auth Service

這是一個簡單的 Spring Boot 使用者認證微服務，支援 JWT 登入、註冊與 Redis 儲存黑名單與 Refresh Token。

## 啟動服務

1. 確保你已安裝 Docker 與 Docker Compose。
2. 使用下列指令啟動服務：

```bash
docker-compose -f docker-compose.yml up --build
```

這會啟動：
- Redis（無密碼）
- Spring Boot 應用程式（會連線 Redis）

## API 說明

- `POST /auth/register`：註冊新使用者
- `POST /auth/login`：登入並取得 JWT Token
- `GET /auth/me`：取得目前登入使用者資訊（需帶 JWT）

## 環境變數（.env）

目前不再使用 Redis 密碼設定，已移除相關環境變數。