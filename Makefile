compile:
	mvn clean package   
	rm -rf ./algorithms/_target
	mkdir ./algorithms/_target
	cp ./chains/target/*.jar ./algorithms/_target/
	cp ./evaluator/target/gleipner.evaluator-1.0-jar-with-dependencies.jar ./algorithms/evaluator.jar
	echo "[INFO] copying custom.so jni lib to /tmp/custom.so, dont forget to delete after benchmarking"
	cp ./chains/src/main/resources/custom.so /tmp/custom.so

compile_alternate:
	cd scripts && python3 compile_for_jchainz.py
	cd scripts/tmp && mvn clean package -DskipTests
	rm -rf ./algorithms/_target
	mkdir ./algorithms/_target
	cp ./scripts/tmp/chains/target/*.jar ./algorithms/_target/

setup:
	cd algorithms && python3 algorithms.py --setup

generate_artificial:
	cd scripts && python3 generate-chains.py --clean
	cd scripts && python3 generate-chains.py --depth 0 19
	cd scripts && python3 generate-chains.py --multipath 10
	cd scripts && python3 generate-chains.py --polymorphism 3 1
	cd scripts && python3 generate-chains.py --polymorphism 3 2
	cd scripts && python3 generate-chains.py --polymorphism 3 3
	cd scripts && python3 generate-chains.py --polymorphism 3 4
	cd scripts && python3 generate-chains.py --polymorphism 4 1
	cd scripts && python3 generate-chains.py --polymorphism 4 2
	cd scripts && python3 generate-chains.py --polymorphism 4 3
	cd scripts && python3 generate-chains.py --polymorphism 5 1
	cd scripts && python3 generate-chains.py --polymorphism 5 2
	cd scripts && python3 generate-chains.py --polymorphism 1000 1
	cd scripts && python3 generate-chains.py --polymorphism 2 1
	cd scripts && python3 generate-chains.py --polymorphism 2 2
	cd scripts && python3 generate-chains.py --polymorphism 2 3
	cd scripts && python3 generate-chains.py --polymorphism 2 4
	cd scripts && python3 generate-chains.py --polymorphism 2 5
	cd scripts && python3 generate-chains.py --polymorphism 2 6
	cd scripts && python3 generate-chains.py --polymorphism 2 7
	cd scripts && python3 generate-chains.py --polymorphism 2 8
	cd scripts && python3 generate-chains.py --polymorphism 2 9
	cd scripts && python3 generate-chains.py --polymorphism 2 10 

compile_so: 
	  g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux chains/src/main/resources/gleipner_chains_jni_Custom001Trampoline.cpp -o chains/src/main/resources/custom.o
	  g++ -shared -fPIC -o chains/src/main/resources/custom.so chains/src/main/resources/custom.o -lc
	  x86_64-w64-mingw32-g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux chains/src/main/resources/gleipner_chains_jni_Custom001Trampoline.cpp -o chains/src/main/resources/custom_win.o
	  x86_64-w64-mingw32-g++ -shared -o chains/src/main/resources/custom_win.dll chains/src/main/resources/custom_win.o -Wl,--add-stdcall-alias
  


