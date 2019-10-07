function [res] = sigmoid_exp_d(x, beta)
    res = 2 .* beta .* sigmoid_exp(x, beta) .* (1 .- sigmoid_exp(x, beta));
end
