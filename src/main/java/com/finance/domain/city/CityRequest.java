package com.finance.domain.city;

import com.finance.domain.state.State;
import lombok.Data;

@Data
public class CityRequest {

  private String name;
  private Long state;

}
