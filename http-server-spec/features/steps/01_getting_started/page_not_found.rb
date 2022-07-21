require "./features/steps/shared.rb"

class Spinach::Features::PageNotFound < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make a GET request to a page that does not exist' do
    @response = Requests.get("/not_found_resource")
  end
end
