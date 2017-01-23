# cw-cm

-- Project --

This project is create using TDD. The source code and the tests are
in separate folder named src and tests.

-- Setters in src.ContactManagerImpl --

Setters in ContactMangagerImpl are created for the purpose of tests gaining access and setting variables in the
class. They should be removed in the real implementation of the class for encapsulation reasons. 
I couldn’t find a way around having them, as the tests
need access to setting variables.

-- Comma separated file --

I chose to use a comma separated file for storing the objects created in the
system. Therefore I have added one extra interface and implementing class, than handles
parsing the objects to text and storing it in a comma separated format, and parsing the text to objects and
reading them back into the system. 

-- Uniqueness of id's --

I chose to use generated hash code for the unique id's. I have added one more interface and
implementing class. The method for generating the id check for collision, by checking the id's of the
objects that are already in the system. 





