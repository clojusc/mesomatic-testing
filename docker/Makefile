DOCKER_ORG=clojusc
IMAGE_NAME=mesos
CONTAINER_ID_FILE=/tmp/clojusc-mesos-container-id

.PHONY: build

build: $(VERSION)/Dockerfile $(VERSION)/build

$(VERSION)/Dockerfile:
	mkdir -p $(VERSION)
	cat warning.txt > $(VERSION)/Dockerfile
	envsubst < Dockerfile.in >> $(VERSION)/Dockerfile

.PHONY: $(VERSION)/build

$(VERSION)/build:
	docker build -t \
				 $(DOCKER_ORG)/$(IMAGE_NAME):$(VERSION) \
				 ./$(VERSION)/

.PHONY: push

push: build $(VERSION)/push

.PHONY: $(VERSION)/push

$(VERSION)/push:
	docker push $(DOCKER_ORG)/$(IMAGE_NAME):$(VERSION)

.PHONY: start

start: build $(VERSION)/start

.PHONY: $(VERSION)/start

$(VERSION)/start:
	-rm $(CONTAINER_ID_FILE)
	-docker run -it \
	            -p 5050:5050 \
	            --cidfile $(CONTAINER_ID_FILE) \
	            $(DOCKER_ORG)/$(IMAGE_NAME):$(VERSION)

.PHONY: daemon-start

daemon-start: build $(VERSION)/daemon-start


.PHONY: $(VERSION)/daemon-start

$(VERSION)/daemon-start:
	-rm $(CONTAINER_ID_FILE)
	-docker run -d \
	            -p 5050:5050 \
	            --cidfile $(CONTAINER_ID_FILE) \
	            $(DOCKER_ORG)/$(IMAGE_NAME):$(VERSION)

.PHONY: shell

shell:
	docker exec -it `cat $(CONTAINER_ID_FILE)` bash

.PHONY: stop

stop:
	docker stop `cat $(CONTAINER_ID_FILE)`
