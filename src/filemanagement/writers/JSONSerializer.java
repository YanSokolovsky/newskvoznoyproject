package filemanagement.writers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class JSONSerializer extends StdSerializer<Writer.data> {

    public JSONSerializer() {
        this(null);
    }

    public JSONSerializer(Class<Writer.data> t) {
        super(t);
    }

    @Override
    public void serialize(
            Writer.data value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.setPrettyPrinter(new DefaultPrettyPrinter());
        gen.writeStartObject();
        gen.writeArrayFieldStart("expressions");
        for (int i = 0 ; i < value.expressions.size(); i++) {
            gen.writeStartObject();
            gen.writeStringField("expression", value.expressions.get(i).expression);
            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeEndObject();
        gen.close();
    }
}