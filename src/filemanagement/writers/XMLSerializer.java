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
        gen.setPrettyPrinter(new DefaultPrettyPrinter());
        gen.writeStartObject();
        gen.writeFieldName("data");
        gen.writeStartObject();
        gen.writeFieldName("expressions");
        gen.writeStartObject();
        for (int i = 0 ; i < value.expressions.size(); i++) {
            gen.writeFieldName("text");
            gen.writeStartObject();
            gen.writeString("expression");//, value.expressions.get(i).expression);
            gen.writeEndObject();
        }
        gen.writeEndObject();
        gen.writeEndObject();
        gen.writeEndObject();
    }
}