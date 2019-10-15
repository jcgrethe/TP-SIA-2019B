function total_error = initial_error(w, input_patterns)

  configuration;
  # Initialization
  v = {};  
  levels = length(w);
  for i = 1 : size(input_patterns)(1)
    current_pattern = input_patterns(i,1:2);  
    v{1} = [-1, current_pattern];
    for k = 1 : levels - 1
       v{k + 1} = [-1, func((w{k} * v{k}')', beta)];
    endfor
    v{levels + 1} =  func((w{end} * v{levels}')', beta);
    output(i) = v{end};
  endfor
  full_data = [input_patterns(:,1:2), output'];


  total_error = 0;
  for i = 1:rows(input_patterns)
    partial_dif = input_patterns(i,3) - full_data(i,3);
    partial_error = (1 / (2 * 2)) * (partial_dif)**2;
    total_error += partial_error;
  endfor
  total_error;

endfunction
