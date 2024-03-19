=====Generate Private Key=====
openssl genpkey -algorithm RSA -out "'PATH_STORE'\private_key.pem""

=====Generate Public Key=====
openssl rsa -pubout -in "'PATH_GET_PRIVATE_KEY'\private_key.pem" -out "'PATH_STORE'\public_key.pem"