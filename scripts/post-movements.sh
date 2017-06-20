#!/bin/bash
echo "Posting $1 movements..." 
echo ""

if [ $# -ne 1 ]; then
    echo $0: usage: Add N value argument 
    exit 1
fi

types=(RESTAURANT HOTEL SUPERMARKET BOOKSHOP TRANSPORT)  
account=(1 2 3)

for (( c=1; c<=$1; c++ ))
do
  type=${types[$RANDOM % ${#types[@]} ]}   
  accountId=${account[$RANDOM % ${#account[@]} ]} 
  amount=$((RANDOM%500+1))  
  printf  '\n Posting --> {"accountId": '$accountId',"type": "'$type'", "amount":'$amount'} \n'
  printf  'Response --> '
  curl -H "Content-Type: application/json" -X POST -d '{"accountId": '$accountId',"type": "'$type'", "amount":'$amount'}'   http://$DOCKER_HOST_IP:90/movement; 
  printf '\n' 
done 