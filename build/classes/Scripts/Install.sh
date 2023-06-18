#!/bin/bash

#The Purpose of this script is to create a AL-G User with sudo permissions.
CurrentUserID=$(id -u)
if [[ "${CurrentUserID}" -ne 0 ]]
then
    echo 'This script neeed to be run as root (su) to proceed'
    exit 1
fi
#Special Characters
SCharacters=$(echo '!@#$%^&*()_-+=' | fold -w1 | shuf | head -c1)
#Declaring the user credentials
ALGUserName='AL-G'
ALGNickName="AL-G The Java Application"
ALGPassCode="$(date +%s%N${RANDOM}${RANDOM}${SCharacters} | sha256sum | head -c32)"
#Adding the user
if [[ "${UID}" -eq 0 ]]
then
    useradd  -c "${ALGNickName}" -m "${ALGUserName}"
    echo "${ALGPassCode}" |  passwd --stdin "${ALGUserName}"
    usermod -aG wheel "${ALGUserName}"
fi

#Checking if user was added successsfully
if [[ "${?}" -eq 0 ]]
then
    echo 'AL-G has installed successfully'
    echo "UserName: ${ALGUserName} | PassCode: ${ALGPassCode}"
elif [[ "${?}" -ge 1 ]]
then
    echo 'ALG was not installed correctly'
    exit 1
fi
