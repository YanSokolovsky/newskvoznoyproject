package filemanagement.writers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class XMLSerializer extends StdSerializer<Writer.data> {

    public XMLSerializer() {
        this(null);
    }

    public XMLSerializer(Class<Writer.data> t) {
        super(t);
    }


    @Override
    public void serialize(
            Writer.data value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.useDefaultPrettyPrinter();
        gen.writeFieldName("data");
        gen.writeStartObject();

        gen.writeEndObject();
    }
}