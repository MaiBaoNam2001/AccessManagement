package com.citynow.accessmanagement.template;

public interface IService <I,O>{
    O execute(I input);
}
