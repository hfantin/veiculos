## contas criadas
auth0 - github - hfantin@gmail.com
mercado pago - backstagefood6@gmail.com
ngrok - ??

## mercado pago - cartoes de teste
Mastercard 5031 4332 1540 6351 123  11/30
Visa       4235 6477 2802 5682 123  11/30
Amex       3753 651535 56885   1234 11/30
Elo Debito 5067 7667 8388 8311 123  11/30

CPF - 12345678909
NOME - APRO 

## usuarios de teste

Buyer Test User  
- 2908928085 - TESTUSER3367302021471928748  iev9t9d37S  - 928085

Seller Test User 
- 2909305387 -  TESTUSER4451468298938495915 Gs6VGh5qoy


curl -i -X GET "https://api.mercadopago.com/v1/payments/34586751249?access_token=APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387"
APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387"
TESTUSER4820435565320605542  - uRCbkzeso0
curl -X GET -H "Authorization: Bearer APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387" https://api.mercadopago.com/v1/payments/1341571987
curl -X GET -H "Authorization: Bearer APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387" https://api.mercadopago.com/v1/payments
curl -X POST https://api.mercadopago.com/users/test_user -H "Authorization: Bearer TEST-5663058348663981-100718-8e9be69bae8ee69a6afa214425648b56-2906463580" -H "Content-Type: application/json" -d '{"site_id":"MLB"}'
{"id":2713619422,"email":"test_user_510369497@testuser.com","nickname":"TESTUSER510369497","site_status":"active","password":"VYfwJKgTWl"
TESTUSER3367302021471928748 2908928085
test_user_3367302021471928748@testuser.com   iev9t9d37S


## consulta pagamentos
> curl -i -X GET "https://api.mercadopago.com/v1/payments/{id}?access_token=APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387"
```json
{
  "msg": "pagamento aprovado com sucesso",
  "saleId": "1",
  "preferenceId": "2909305387-648e2175-1cc5-47d1-a00b-38340860b1f0",
  "status": "approved",
  "collectionId": "1325010828",
  "paymentType": "account_money",
  "orderId": "34587994367"
}
```



### TOKEN 
#### no navegador

eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ilc2YlRZMkh5bjhYZ0xjZHhmeWt6TCJ9.eyJpc3MiOiJodHRwczovL2hmYW50aW4udXMuYXV0aDAuY29tLyIsInN1YiI6Imdvb2dsZS1vYXV0aDJ8MTA3MTEwODE1NjAyNTYzNTAwNjI0IiwiYXVkIjpbImh0dHBzOi8vdmVpY3Vsb3MvYXBpIiwiaHR0cHM6Ly9oZmFudGluLnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE3NTk5NTk1MTIsImV4cCI6MTc2MDA0NTkxMiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImF6cCI6IkFiV0RFQm1yM2tBSTFlYXhCQzRQTHNHSVlYaEZ0R0ZEIn0.OZVwHSrY-2t42Nqb9ECYx86KWDd4OFaRadSr9FpurmOp_WjAafBFJGZuy0p5UzE4S08h-P2bY6YKj2icWd0okeikEEgVV8DBS4D4y6ZT-PBqYaBZnOctVD2KZrzcikUbpQw3Tidm2Nj9414bdoKvbFr1T9aWhpQKqHVUrbjPlJazatz4Cyp_b0ey9mDKtHEfbHBCj6HLF2-sP5plPfDSQT2BeR19opBTzIB9pGICQiIaLG047vpwXq2sH4cy-5FQ2x1GJNZvQVakedbGqFMhkalpz-t0C_bdZ8R0QWuJ45cNTgnk5bfldnR-q1yqGxP-crQjMfTVd6tm4MRHof5izQ


### access token postman
eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIiwiaXNzIjoiaHR0cHM6Ly9oZmFudGluLnVzLmF1dGgwLmNvbS8ifQ..PDdVpOGDWSdiL1m_.AiHJgj5j-m1bGeyHcylC0O1Xn0RX09QYX8wFU-pwSCKvvHussdissZRBm2MGR17qCRsAf54jA0PKLjCRtMSwdf4ULMxrme_UbbYMXFkcCspZD-znSyYsAOcRlNARXYrdewRjEd3B8I6giPzModsJMvYOb1j65xAAJgDRsCW4TQPLrRzjPVAiNl-1VSkaDrpRFhy2y4E6jV6w5yWng3RicepAQedXt21GIntw0K-DvqQaRojuL8S7wtSLI_XCjpIf_r1gsSa1TNuu6xkDYWkKtUo3hW801u6N7YuuNWP2MH5U9l3MWcEqIl-ukvD_.ec0vc1C4f7MG27udwprOLg
### id_token postman
eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ilc2YlRZMkh5bjhYZ0xjZHhmeWt6TCJ9.eyJnaXZlbl9uYW1lIjoiSGFtaWx0b24iLCJuaWNrbmFtZSI6ImhmYW50aW4iLCJuYW1lIjoiSGFtaWx0b24iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUNnOG9jS3l1Q0Jzc0pFRlVObF8tUFVYSWpqejUwOHRKVnBsVVZwN0VCaGxwU1JFR1Nhc196a0k9czk2LWMiLCJ1cGRhdGVkX2F0IjoiMjAyNS0xMC0wOFQyMTozOTo1Ni42ODZaIiwiZW1haWwiOiJoZmFudGluQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJpc3MiOiJodHRwczovL2hmYW50aW4udXMuYXV0aDAuY29tLyIsImF1ZCI6IkFiV0RFQm1yM2tBSTFlYXhCQzRQTHNHSVlYaEZ0R0ZEIiwic3ViIjoiZ29vZ2xlLW9hdXRoMnwxMDcxMTA4MTU2MDI1NjM1MDA2MjQiLCJpYXQiOjE3NTk5NTk1OTgsImV4cCI6MTc1OTk5NTU5OCwic2lkIjoiaXZTYWhCZWVTZnAtNTBRMko4UXZjSm1RUnYxRzBnNW0ifQ.S6APKmfBXWhMlczFH2vus0fObmkN3Sx3CmSDMPjQNmthrETQcmmQarpvpIxCw3wZr_U70SZtVW8DJMHPjGRXsLMpws9ZzGIr0gX-SlrG9Sbc1eCtBJUHTT4CO5tP3N9PoXDzwRaYEa1no6QCBSeM3aQ1fXK028YzPpyGtAQu5Zp_9Ng_wCWzROmtQEPwRBO_3S743Hdu-sZbKSc1bOPb1D8RpShI0EPTcUXanw_VyLdM26UpS9SwEuon6EYphWAxk9ozKIT-ZEQdw1fZXFaE8wlVtgQrI9nUkHCOZ2rUhZXU6Xda56Z1lK1TjxU7cK2bceCOB0g9HB7yG7fBR9qDVw