DIR=/home/masateruteru/projects/j9cc
SOURCE=*.java
compile:
	javac $(DIR)/$(SOURCE)

test:
	./test.sh

clean:
	rm -f j9cc *.class *~ tmp* *.s
