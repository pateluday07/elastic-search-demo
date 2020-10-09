#!/bin/bash
# script to create indexes if not exists

readonly ELASTIC_SEARCH_HOST=elasticsearch-for-elastic-search-demo:9200
readonly INDEX_NAME_FOR_CUSTOMER_DOMAIN=customer
readonly INDEX_NAME_FOR_ORDER_DOMAIN=order

create_customer_index(){
  while true ; do
      http_code="$(curl --write-out '%{http_code}' --silent --output /dev/null -I $ELASTIC_SEARCH_HOST/$INDEX_NAME_FOR_CUSTOMER_DOMAIN)"
      if [ $http_code -ne 200 ]; then
          echo "$INDEX_NAME_FOR_CUSTOMER_DOMAIN index is not available, we are creating one..."
          curl -X PUT $ELASTIC_SEARCH_HOST/$INDEX_NAME_FOR_CUSTOMER_DOMAIN
      elif [ $http_code -eq 200 ]; then
          echo " $INDEX_NAME_FOR_CUSTOMER_DOMAIN index is ready."
          break
      fi
  done
}

create_order_index(){
  while true ; do
      http_code="$(curl --write-out '%{http_code}' --silent --output /dev/null -I $ELASTIC_SEARCH_HOST/$INDEX_NAME_FOR_ORDER_DOMAIN)"
      if [ $http_code -ne 200 ]; then
          echo "$INDEX_NAME_FOR_ORDER_DOMAIN index is not available, we are creating one..."
          curl -X PUT $ELASTIC_SEARCH_HOST/$INDEX_NAME_FOR_ORDER_DOMAIN
      elif [ $http_code -eq 200 ]; then
          echo " $INDEX_NAME_FOR_ORDER_DOMAIN index is ready."
          break
      fi
  done
}

while true; do
  status="$(curl -s $ELASTIC_SEARCH_HOST/_cluster/health?pretty|grep status|awk '{print $3}'|cut -d\" -f2)"
  echo "elasticsearch is starting..."
  if [ "$status" != "" ]; then
      echo "elasticsearch status is $status"
  fi

  if [ "$status" = "green" ] || [ "$status" = "yellow" ]
  then
    create_customer_index
    create_order_index
    break
  fi

  sleep 5
done

echo "elasticsearch is ready for use."

exec "$@"