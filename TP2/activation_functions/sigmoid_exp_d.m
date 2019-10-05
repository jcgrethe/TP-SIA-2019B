function [res] = sigmoid_exp_d(x)
    res = 2 .* sigmoid_exp(x) .* (1 .- sigmoid_exp(x));
end
