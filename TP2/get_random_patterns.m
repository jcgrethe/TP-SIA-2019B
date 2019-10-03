function [input_patterns] = get_random_patterns(dataset_path, patterns_percentage)
  dataset = dlmread(dataset_path)(2:end, :);
  pattern_qty = round( (patterns_percentage / 100) * rows(dataset));
  training_set = randperm(rows(dataset))(1:pattern_qty);  
  for i = 1:pattern_qty
    input_patterns(i, :) = dataset(training_set(i), 1:end);
  endfor
endfunction