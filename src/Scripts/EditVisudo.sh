#!/bin/bash

#The purpose of this script is to edit the open the visudo file;

CurrentUserID=$(id -u)
if [[ "${CurrentUserID}" -ne 0 ]]
then
    echo 'This script must not to be run as root (su) to proceed'
    exit 1
fi

#Opening visudo and add ing AL-G
echo 'AL-G ALL= NOPASSWD: /usr/sbin/smartctl -a' | sudo EDITOR='tee -a' visudo
#Checking if the script has ran successfully
if [[ "${?}" -eq 0 ]]
then
    echo 'AL-G has been added to sudoers successfully'
elif [[ "${?}" -ne 0 ]]
then
    echo 'AL-G has not been added to sudoers successfully'
    exit 1
fi
