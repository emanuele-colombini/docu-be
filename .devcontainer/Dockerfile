FROM ubuntu

RUN apt-get update
RUN apt-get -y install curl wget git zip unzip

# installing sdkman
RUN curl -s "https://get.sdkman.io" | bash
RUN /bin/bash -c "source /root/.sdkman/bin/sdkman-init.sh; sdk version; sdk install java 11.0.10.9.1-amzn; sdk install maven"
