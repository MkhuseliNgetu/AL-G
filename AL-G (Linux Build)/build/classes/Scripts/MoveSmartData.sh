#!/bin/bash

#The Purpose of this script is to move the Drive Attribute Data to the users directory from root.
CurrentUserID=$(id -u)
if [[ "${CurrentUserID}" -ne 0 ]]
then
    echo 'This script needs to be run as root (su) to proceed'
    exit 1
fi

#Moving Drive Attribute data
DriveAttributeData="SMART.txt"
MainUser=$(id -nu "1000")
MoveData="$(mv -v /home/"${DriveAttributeData}" /home/"${MainUser}"/)"
AddPermissions="$(chown "${MainUser}":"${MainUser}" /home/"${MainUser}"/"${DriveAttributeData}")"

#Checking if the script has ran successfully
if [[ "${?}" -eq 0 ]]
then
    echo 'SMART data has been moved successfully'
elif [[ "${?}" -ne 0 ]]
then
    echo 'SMART data was not moved successfully'
    exit 1
fi
