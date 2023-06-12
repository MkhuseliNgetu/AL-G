#!/bin/bash

#The purpose of this script is to automate the proccess of switch to the the AL-G user.
CurrentUID="$(id -u)"
if [[ "${CurrentUID}" -eq 0 ]]
then
  echo 'This script must not be run as a root user. Please switch a non-root user.'
fi

#Switch to AL-G user
SwitchUser(){

  DesiredUserPasscode="${1}"

  ExecuteMe="$(echo ${DesiredUserPasscode} | sudo -S  sudo smartctl -a /dev/sdd1)"

  sleep 5
}

#Executing switch with parameters
SwitchUser "${1}"

#Checking if the script has executed successfully
if [[ "${?}" -eq 0 ]]
then
  echo 'User has been changed successfully'
else
  echo 'User has not been changed'
fi
