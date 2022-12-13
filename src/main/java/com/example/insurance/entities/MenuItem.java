package com.example.insurance.entities;

import java.io.IOException;

public interface MenuItem {
    String getInformation();
    void execute() throws IOException;
}
