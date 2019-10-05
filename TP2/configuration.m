hidden_layers = [20 20 20 3];
func = @hyp_tan;
func_d = @hyp_tan_d;

#Data init	
v = {[-1 * ones(1, rows(input_patterns)); input_patterns(1:end,1:end - 1)']'};
v{1} = v{1}';
S = [input_patterns(:,end)];
for i = 1:length(hidden_layers)
    v{i + 1} = [-1 * ones(1, rows(input_patterns)); zeros(rows(input_patterns), hidden_layers(i))']';
    v{i + 1} = v{i + 1}';
    d{i + 1} = zeros(hidden_layers(i), 1);
endfor
v{end + 1} = zeros(1, rows(input_patterns));
d{end + 1} = zeros(1, 1);

#Weights and deltas
w = {rand(hidden_layers(1), columns(input_patterns)) - 0.5};
dw = {zeros(hidden_layers(1), columns(input_patterns))};
for i = 1:length(hidden_layers) - 1
    w{i + 1} = rand(hidden_layers(i + 1), hidden_layers(i) + 1) - 0.5;
    dw{i + 1} = zeros(hidden_layers(i + 1), hidden_layers(i) + 1);
endfor
w{end + 1} = rand(1, hidden_layers(end) + 1) - 0.5;
dw{end + 1} = zeros(1, hidden_layers(end) + 1);
epoch = 0;

# ETAs config values
adaptative_eta = false;
eta = 0.01;
eta_a = 0.0001;
eta_b = 0.05;
eta_min = 0.005;
eta_max = 0.015;

# Error config
MAX_ERROR = 0.0001;
total_error = 1;
last_error = 99999999;
MAX_EPOCH = 1500;