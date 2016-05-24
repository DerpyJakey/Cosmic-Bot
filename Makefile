ROOT=./com/org/derpyjakey

build:
	mkdir -p ./build/com/org/derpyjakey
	javac -d ./build ${ROOT}/*.java ${ROOT}/frames/*.java ${ROOT}/references/*.java ${ROOT}/utilities/*.java
	cd ./build && jar -cfe CosmicBot.jar com.org.derpyjakey.CosmicBot ${ROOT}/*.class \
	${ROOT}/frames/*.class ${ROOT}/references/*.class \
	${ROOT}/utilities/*.class

clean:
	rm -rfv *~ ./build
