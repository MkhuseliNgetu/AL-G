#!/bin/bash

#The purpose of this script is to edit the open the visudo file;

CurrentUserID=$(id -u)
if [[ "${CurrentUserID}" -eq 0 ]]
then
    echo 'This script must be run as a super user to proceed'
    exit 1
fi
CurrentUser=$(id -un)
#Opening visudo and adding AL-G
AddingToVisudo=$(echo "${CurrentUser} ALL=NOPASSWD:/usr/sbin/smartctl" | sudo EDITOR='tee -a' visudo)
#Checking if the script has ran successfully
if [[ "${?}" -eq 0 ]]
then
    echo "${CurrentUser} has been added to sudoers successfully"
elif [[ "${?}" -ne 0 ]]
then
    echo "${CurrentUser} has not been added to sudoers successfully"
    exit 1
fi
