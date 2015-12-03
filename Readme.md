#Inspections for a IntelliJIdea IDE.

##Just now, this set contains four inspections:     
* Check for name of a boolean property getter     
* Check for a probably getter   
* Check for a clashing getter names     
* Check for a methods name started from  modal verb and probably getter     

##All inspections is on by default.    
##Check for name of a boolean property getter     
In accordance with the JavaBeans specification section 8.3.2:    
<p>Boolean properties:     
In addition, for boolean properties, we allow a getter method to match the pattern:
    public boolean is<PropertyName>();This "isPropertyName" method may be provided instead of a "get<PropertyName>" method,or it may be provided in addition to a "get<PropertyName>" method. In either case,if the is<PropertyName> method is present for a boolean property then we will usethe "is<PropertyName>" method to read the property value. An example boolean property might be:</p>
<em>public boolean isMarsupial(); <br> public void setMarsupial(boolean m);</em>
##Check for a probably getter   
This method have only return instructions. Probably this is a getter.
##Check for a clashing getter names
Assume you have two methods with signatures 'getX()' and 'isX()'.      
Both methods are getters for property 'x'. So the result of invoking
'foo.x' can be unexpected.
This inspection reports methods which have such a problem.
##Check for a methods name started from  modal verb and probably getter
This method started from a modal verb. Probably this is a getter.