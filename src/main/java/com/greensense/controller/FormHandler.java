package com.greensense.controller;

import com.greensense.view.components.Form;

import java.util.Map;

public interface FormHandler {
    boolean handleFormSubmission(Map<String, Object> formData, Form form);
}
