package pl.training.shop.commons.money;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FastMoneyMapper {

    default String toText(FastMoney value) {
        return value != null ? value.toString() : "";
    }

    default FastMoney toMoney(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return FastMoney.parse(value);
    }

}
