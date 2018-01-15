# UnifyIDChallenge

For this challenge, I obtained 20 random numbers in the range of -128 to 127 from random.org and cast them to bytes, creating an array. I then reseeded the SecureRandom object with this byte array and used it to create an RSA keypair using Java's KeyPairGenerator. One other approach could have been to call the SecureRandom constructor with the byte array as seed, however this seemed the more elegant option.
