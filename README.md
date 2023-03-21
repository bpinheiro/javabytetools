# javabytetools

Working with bytes on java can be easy and funny.

## Usage

Here is listed some examples to help you to understand how to use this routines

```java
//Test with 2 byte in little endian format 
data = new byte[]{0x12, 0x34};
check = 13330;
number  = ByteArray.byteToInt(data, 0, 2, true);
System.out.println("Array [" + ByteArray.byteToHex(data, ",") + "] in little endian is: " + number + " valid: " + (number == check));
```

### TODO

Implements a signed mode for all types
