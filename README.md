# cw-cm

-- Setters in ContactManagerImpl --

Setters in ContactMangagerImpl are created for the purpose of tests gaining access and setting variables in the
class. They should be removed in the real implementation of the class for encapsulation reasons. 
I couldn’t find a way around having them, as the tests
need access to setting variables.

-- Uniqueness of ID's --

The ids are created by hashcode and thus are not truly unique, therefore some type of coalition may occur. 
In order to make the ids truly unique I would need to implement a mechanism, that checks the ids of the objects in memory. 
This I have omitted due to limited time and as its not a requirements   



