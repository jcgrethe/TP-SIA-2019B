addpath("./activation_functions");

#Just one output

function [w] = incremental_trainer(dataset, percentage, hidden_layers)

	#Get X% of random normalized patterns from dataset
	input_patterns = get_random_patterns(dataset, percentage);

  #Data init	
	v = {[-1 * ones(1, rows(input_patterns)); input_patterns(1:end,1:end - 1)']'};
  o = [input_patterns(1:end,end - 1)];
	for i = 1:length(hidden_layers)
		v{i + 1} = [-1 * ones(1, rows(input_patterns)); zeros(rows(input_patterns), hidden_layers(i))']';
    d{i + 1} = zeros(rows(input_patterns), hidden_layers(i));
	endfor
  v{end + 1} = zeros(rows(input_patterns), 1);
  d{end + 1} = zeros(rows(input_patterns), 1);
 
  #Weights
  w = {rand(hidden_layers(1), columns(input_patterns)) .* 1/sqrt(columns(input_patterns) - 1) - 0.5};
  for i = 1:length(hidden_layers) - 1
    w{i + 1} = rand(hidden_layers(i + 1), hidden_layers(i) + 1) .* 1/sqrt(hidden_layers(i)) - 0.5;
  endfor
  w{end + 1} = rand(1, hidden_layers(end) + 1) .* 1/sqrt(hidden_layers(end)) - 0.5;
    
  epochs = 0;
  eta = 0.05;
  MAX_ERROR = 0.01;
  global_error = 1;


  while(epochs < 1)

    #TODO: Shuffle patterns taking random p;
    
    for p = 1:rows(input_patterns)
      
      
      for i = 1:length(hidden_layers) + 1
         v{i + 1}(p,2:end) = sigmoid_exp((w{i} * v{i}(p,:)')');
      endfor
      v{end}(p,:) = sigmoid_exp((w{end} * v{end-1}(p,:)')');


      

      #TODO: operar ultima capa V
      #TODO:operar ultima capa w


    endfor

    epochs++;
  end


endfunction

incremental_trainer("terrain02.data",40, [2]);