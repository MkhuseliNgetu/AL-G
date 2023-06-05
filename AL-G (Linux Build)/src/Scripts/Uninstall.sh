#!/bin/bash

#The purpose of this script is to remove the ALG user frpm the application.
#Check if the user is running the script with admin rights.
CurrentUserID=$(id -u)
if [[ "${CurrentUserID}" -ne 0 ]]
then
    echo 'This script needs to be run as root (su) to proceed'
    exit 1
fi

#Declaring the User
ALG="AL-G"

#Deleting the user and directories
userdel "${ALG}" -r

#Checking if the script has ran successfully
if [[ "${?}" -eq 0 ]]
then
    echo "ALG has been removed successfully"
elif [[ "${?}" -ne 0 ]]
then
    echo "ALG was not removed correctly. Please run the script again by putting 'ALG' as a argument when executing the script"
    exit 1
fi
