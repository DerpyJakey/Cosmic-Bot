ROOT=./com/org/derpyjakey

build:
	mkdir -p ./build/com/org/derpyjakey
	javac -d ./build ${ROOT}/*.java ${ROOT}/frame/*.java ${ROOT}/reference/*.java ${ROOT}/utilities/*.java
	cd ./build && jar -cvfm CosmicBot.jar ../Manifest/Manifest.txt ${ROOT}/*.class \
	${ROOT}/frame/*.class ${ROOT}/reference/*.class \
	${ROOT}/utilities/*.class

clean:
	rm -rfv *~ ./build
