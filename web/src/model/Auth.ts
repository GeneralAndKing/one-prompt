export interface AuthCode {
  base64Code: string
  authCode: string
}

export interface UserToken {
  id?: string
  subject?: string
  username?: string
  accessToken?: string
  refreshToken?: string
}
