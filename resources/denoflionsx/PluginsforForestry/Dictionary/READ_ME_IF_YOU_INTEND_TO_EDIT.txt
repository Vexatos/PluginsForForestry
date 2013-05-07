These files are for PfF 3.X's integration system.

You may create a new text file and list it in either OreDictionaryModules.txt or ReflectionModules.txt. THESE FILES DO NOT HAVE TO BE REPLACED IN THE JAR AFTER EDITING! STICK THEM IN config/denoflionsx/PluginsforForestry instead! It will load files in the config folder over ones in the jar.

Optional Fields:
# Title: <title here>
# Author: <your name here>

Required Fields:
# Amount: <amount of liquid here. i.e. 1000>
# Type: <type here>

You can also leave comments in the file as long as the line starts with a #. All whitespaces are ignored during parsing.

See VALID_TYPES.txt for types

Reflection Module Notes:

Targeted fields MUST BE STATIC AND ACCESSIBLE.