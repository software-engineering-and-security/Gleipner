log_output = '''Running on _target/gleipner.chains-1.0-ysoserial-cc1.jar at 2024-05-11 13:07:00.940950
	[serianalyzer::all] execution completed in 0.12117616832256317 seconds

Running on _target/gleipner.chains-1.0-jni.jar at 2024-05-11 13:07:01.234285
	[serianalyzer::all] execution completed in 0.15360911190509796 seconds

Running on _target/gleipner.chains-1.0-reflection-metaobjects.jar at 2024-05-11 13:07:01.550557
	[serianalyzer::all] execution completed in 0.17376988753676414 seconds

Running on _target/gleipner.chains-1.0-ysoserial-myfaces.jar at 2024-05-11 13:07:01.909640
	[serianalyzer::all] execution completed in 0.14181867986917496 seconds

Running on _target/gleipner.chains-1.0-ysoserial-urldns.jar at 2024-05-11 13:07:02.241519
	[serianalyzer::all] execution completed in 0.14423895627260208 seconds

Running on _target/gleipner.chains-1.0-ysoserial-spring.jar at 2024-05-11 13:07:02.531970
	[serianalyzer::all] execution completed in 0.1465734150260687 seconds

Running on _target/gleipner.chains-1.0-ysoserial-aspectjweaver.jar at 2024-05-11 13:07:02.861150
	[serianalyzer::all] execution completed in 0.1450695227831602 seconds

Running on _target/gleipner.chains-1.0-polymorphism.jar at 2024-05-11 13:07:03.180384
	[serianalyzer::all] execution completed in 3.2262178640812635 seconds

Running on _target/gleipner.chains-1.0-reflection-classloading.jar at 2024-05-11 13:07:06.577004
	[serianalyzer::all] execution completed in 0.12986212223768234 seconds

Running on _target/gleipner.chains-1.0-ysoserial-clojure.jar at 2024-05-11 13:07:06.868450
	[serianalyzer::all] execution completed in 0.16063408739864826 seconds

Running on _target/gleipner.chains-1.0-ysoserial-vaadin.jar at 2024-05-11 13:07:07.204057
	[serianalyzer::all] execution completed in 0.1614773329347372 seconds

Running on _target/gleipner.chains-1.0-multipath.jar at 2024-05-11 13:07:07.550197
	[serianalyzer::all] execution completed in 0.14725528471171856 seconds

Running on _target/gleipner.chains-1.0-reflection-proxy.jar at 2024-05-11 13:07:07.886969
	[serianalyzer::all] execution completed in 0.13625767454504967 seconds

Running on _target/gleipner.chains-1.0-reflection-methodinvoke.jar at 2024-05-11 13:07:08.173725
	[serianalyzer::all] execution completed in 0.15614543668925762 seconds

Running on _target/gleipner.chains-1.0-depth.jar at 2024-05-11 13:07:08.502586
	[serianalyzer::all] execution completed in 0.2610500678420067 seconds

Running on _target/gleipner.chains-1.0-serialization-variants.jar at 2024-05-11 13:07:08.944647
	[serianalyzer::all] execution completed in 0.12791165709495544 seconds

Running on _target/gleipner.chains-1.0-reflection-exceptions.jar at 2024-05-11 13:07:09.262348
	[serianalyzer::all] execution completed in 0.17129430547356606 seconds

Running on _target/gleipner.chains-1.0-keywords.jar at 2024-05-11 13:07:09.598032
	[serianalyzer::all] execution completed in 0.13445221818983555 seconds

Running on _target/gleipner.chains-1.0-ysoserial-groovy.jar at 2024-05-11 13:07:09.886524
	[serianalyzer::all] execution completed in 0.1423899233341217 seconds

Running on _target/gleipner.chains-1.0-reflection-clinit.jar at 2024-05-11 13:07:10.209468
	[serianalyzer::all] execution completed in 0.1498486753553152 seconds

Running on _target/gleipner.chains-1.0-ysoserial-hibernate.jar at 2024-05-11 13:07:10.540016
	[serianalyzer::all] execution completed in 0.14319363981485367 seconds

Running on _target/gleipner.chains-1.0-reflection-constructor.jar at 2024-05-11 13:07:10.845069
	[serianalyzer::all] execution completed in 0.14444674365222454 seconds

Running on _target/gleipner.chains-1.0-ysoserial-jrmplistener.jar at 2024-05-11 13:07:11.146389
	[serianalyzer::all] execution completed in 0.1496569737792015 seconds

Running on _target/gleipner.chains-1.0-lambda.jar at 2024-05-11 13:07:11.472561
	[serianalyzer::all] execution completed in 0.13779008015990257 seconds

Running on _target/gleipner.chains-1.0-payload-construction.jar at 2024-05-11 13:07:11.783325
	[serianalyzer::all] execution completed in 0.179730711504817 seconds

Running on _target/gleipner.chains-1.0-basic.jar at 2024-05-11 13:07:12.126532
	[serianalyzer::all] execution completed in 0.11238120123744011 seconds

Running on _target/gleipner.chains-1.0-ysoserial-rome.jar at 2024-05-11 13:07:12.403204
	[serianalyzer::all] execution completed in 0.16391323320567608 seconds
'''
do_sum = False
total_seconds = 0

for line in log_output.split("\n"):
    if "Running on" in line:
        if "ysoserial" in line:
            do_sum = True
        else:
            do_sum = False
            
    if "::all" in line and do_sum:
        total_seconds += float(line.strip().split(" ")[-2])
        
print(total_seconds)
    
