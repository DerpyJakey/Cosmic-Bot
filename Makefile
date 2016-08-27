ROOT=./src/com/org/derpyjakey
COMPILEDROOT=./com/org/derpyjakey

build:
	mkdir -p ./build/com/org/derpyjakey
	javac -d ./build ${ROOT}/*.java ${ROOT}/frames/*.java ${ROOT}/references/*.java ${ROOT}/utilities/*.java
	cd ./build && jar -cfe CosmicBot.jar com.org.derpyjakey.CosmicBot ${COMPILEDROOT}/*.class \
	${COMPILEDROOT}/frames/*.class ${COMPILEDROOT}/references/*.class \
	${COMPILEDROOT}/utilities/*.class

rebuild:
	rm -rfv *~ ./build
	mkdir -p ./build/com/org/derpyjakey
	javac -d ./build ${ROOT}/*.java ${ROOT}/frames/*.java ${ROOT}/references/*.java ${ROOT}/utilities/*.java
	cd ./build && jar -cfe CosmicBot.jar com.org.derpyjakey.CosmicBot ${COMPILEDROOT}/*.class \
	${COMPILEDROOT}/frames/*.class ${COMPILEDROOT}/references/*.class \
	${COMPILEDROOT}/utilities/*.class

clean:
	rm -rfv *~ ./build
