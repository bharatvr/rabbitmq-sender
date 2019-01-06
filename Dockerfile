FROM tomcat:7.0.91-jre8

# Delete existing ROOT folder
RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY MaxiqWebApp/target/MaxiqWebApp.war  /usr/local/tomcat/webapps/ROOT.war


# set env variables associated with mosaic
ENV MAXIQ_HOME=/mosaic
ENV MAXIQ_VERSION=0.0.1

COPY k8/mosaic /mosaic

#Config
ADD k8/config.properties /mosaic/conf/config.properties

ADD k8/error_logs.properties /mosaic/conf/error_logs.properties

ADD k8/error_conf_maxiqweb.xml /mosaic/conf/error_conf_maxiqweb.xml

ADD k8/job-scheduler.properties /mosaic/conf/job-scheduler.properties

COPY k8/catalina.sh /usr/local/tomcat/bin/

COPY k8/libs/* /usr/local/tomcat/lib/

ADD k8/entrypoint.sh /entrypoint.sh

EXPOSE 8080

# define entrypoint
ENTRYPOINT /bin/bash /entrypoint.sh
