#!/bin/bash

#Check if the user is running the script with admin rights.
CurrentUserID=$(id -u)
if [[ "${CurrentUserID}" -eq 0 ]]
then
    echo 'This script must not be run as root (su) to proceed'
    exit 1
fi

#Get S.M.A.R.T data
ChosenHDD="${1}"
FindSMARTData=$(sudo smartctl -a /dev/"${ChosenHDD}" | tee Holding.txt && cat Holding.txt | sed -n '57,82p' | tee SMART.txt)
RemoveHoldingFromCurrentLocation=$(rm -rf Holding.txt)


#Checking if the script has ran successfully
if [[ "${?}" -eq 0 ]]
then
    echo 'SMART data has been obtained successfully'
elif [[ "${?}" -ne 0 ]]
then
    echo 'SMART data was not obtained successfully'
    exit 1
fi
