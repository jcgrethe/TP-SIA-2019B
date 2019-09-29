addpath("./activation_functions");

function weights = incremental_trainer(percentage, hidden_layers)

	#Get X% of random normalized patterns from dataset
	input_patterns = get_random_patterns(dataset, percentage);
	#e = input_patterns(1:end - 1);
	#s = input_patterns(end:end);

  	#Data init
  	
  	v = {[-1 * ones(1, rows(input_patterns)); input_patterns(1:end - 1)']};
  	for i = 2:length(hidden_layers)
  		v{i} = [-1, zeros(rows(input_patterns))];
  	endfor






endfunction
