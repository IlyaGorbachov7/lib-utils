package by.gorbachevid.perse.resbndl.resolver;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder(builderClassName = "Builder")
@AllArgsConstructor
public class ResolverToLong implements Resolver<Long> {
    @Override
    public Long assemble(String value) {
        value = value.trim();
        return Long.parseLong(value);
    }

    @Override
    public String assembleToString(Long object) {
        return String.valueOf(object.longValue());
    }
}
