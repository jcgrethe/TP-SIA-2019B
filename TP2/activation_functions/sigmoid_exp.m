function [res] = sigmoid_exp(x)
    res = 1.0 ./ (1.0 + exp(-2 * x));
end
