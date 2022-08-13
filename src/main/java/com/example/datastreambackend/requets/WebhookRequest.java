package com.example.datastreambackend.requets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebhookRequest {

    private String event;

    private Map<String, Object> data;
}
