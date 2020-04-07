DIR=/home/masateruteru/projects/j9cc
SOURCE=j9cc.java
compile:
	javac $(DIR)/$(SOURCE)

test:
	./test.sh

clean:
	rm -f j9cc *.class *~ tmp* *.s
