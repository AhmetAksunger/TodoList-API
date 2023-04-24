package com.ahmetaksunger.utils.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

    ModelMapper forRequest();
    ModelMapper forResponse();

}
