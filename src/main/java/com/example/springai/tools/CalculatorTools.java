package com.example.springai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class CalculatorTools {

  @Tool(name = "add-numbers", description = "Add two numbers")
  public double add(
      @ToolParam(description = "The first number") double first,
      @ToolParam(description = "The second number") double second) {
    return first + second;
  }

  @Tool(name = "multiply-numbers", description = "Multiply two numbers")
  public double multiply(
      @ToolParam(description = "The first number") double first,
      @ToolParam(description = "The second number") double second) {
    return first * second;
  }
}
