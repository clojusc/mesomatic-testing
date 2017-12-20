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
	docker build -t $(DOCKER_ORG)/$(IMAGE_NAME):$(VERSION) ./$(VERSION)/

.PHONY: push

push: build $(VERSION)/push

.PHONY: $(VERSION)/push

$(VERSION)/build:
	docker push $(DOCKER_ORG)/$(IMAGE_NAME):$(VERSION)

.PHONY: run

run: build $(VERSION)/run

.PHONY: $(VERSION)/run

$(VERSION)/run:
	-docker run -it $(DOCKER_ORG)/$(IMAGE_NAME):$(VERSION)

.PHONY: daemon-start

daemon-start: build $(VERSION)/daemon-start


.PHONY: $(VERSION)/daemon-start

$(VERSION)/daemon-start:
	-rm $(CONTAINER_ID_FILE)
	-docker run -d --cidfile $(CONTAINER_ID_FILE) \
	            $(DOCKER_ORG)/$(IMAGE_NAME):$(VERSION)

.PHONY: daemon-stop

daemon-stop:
	docker stop `cat $(CONTAINER_ID_FILE)`
