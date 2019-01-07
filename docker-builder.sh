echo "==================Building rabbitmq producer jar==================="
mvn clean install
echo "+++++++++++++ rabbitmq producer jar build complete +++++++++++++++++"


echo -en '\n\n\n'


echo "================ Deleting old docker image =============="
docker rmi -f rabbitmq-producer:1.0.0
echo "+++++++++++++++++ Delete complete +++++++++++++++++++++++"


echo -en '\n\n\n'


echo "==================== Building docker image =============="
docker build -t rabbitmq-producer:1.0.0 .
echo "++++++++++++++++++++ Docker image build complete +++++++++++++++++++"