function [input_patterns] = get_random_patters(dataset_path, patterns_percentage)
  dataset = dlmread(dataset_path)(2:end, :);
  pattern_qty = round(patterns_percentage * rows(dataset));
  training_set = randperm(rows(dataset))(1:pattern_qty);  
  for i = 1:pattern_qty
    input_patterns(i, :) = dataset(training_set(i), 1:end);
  endfor
  % for i = 1:columns(input_patterns)
  %   # https://datascience.stackexchange.com/questions/13178/how-to-normalize-data-for-neural-network-and-decision-forest
  %   minX = min(input_patterns(:,i));
  %   maxX = max(input_patterns(:,i));
  %   input_patterns(:, i) = (input_patterns(:,i) - minX) / (maxX-minX) - 0.5;
  % endfor
endfunction